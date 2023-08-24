using Microsoft.EntityFrameworkCore;
using Services.DataBaseService;
using Models;
using Org.BouncyCastle.Bcpg;
using WebApplication1.DataTransfersObjects;


namespace FlexibleSchedule.Services.DataBaseService;

public class GroupRepository : IGroupRepository
{
    private IGroupRepository _groupRepositoryImplementation;
    
    private Context _context;
    
    public GroupRepository(Context context)
    {
        _context = context;
    }

    public bool Create(Group group) //todo добавить проверку, чтобы пользователь не мог создать больше двух расписаний!!!
    {
        User creator = _context.Users.FirstOrDefault(sp=>sp.id==group.CreatorId)!;
        if (_context.Groups.Count(g => g.CreatorId == creator.id)<2)
        {
            _context.Days.AddRange(group.TimeTable.Days);

            GroupsUsers groupsUsers = new GroupsUsers()
            {
                Groups = group,
                GroupId = group.id,
                Users = creator,
                UserId = creator.id,
                Priority = GetLastPriority(creator.id)+1
            };
            
            _context.GroupsUsers.Add(groupsUsers);
            
            group.id = _context.SaveChanges();
            
            return true;
        }

        return false;
        

    }

    public void ConnectToGroup(ConnectGroupDto dto,int UserId)
    {
        User user = _context.Users
            .Include(u=>u.Groups)
            .FirstOrDefault(sp=>sp.id==UserId)!;
        
        Group group = _context.Groups
            .FirstOrDefault(g => g.Code == dto.Code)!;
        
        user.Groups.Add(group);
        
        _context.SaveChanges();
        
        var groupsUsers = _context.GroupsUsers.FirstOrDefault(g => g.GroupId == group.id && g.UserId == UserId);
        
        groupsUsers.Priority = GetLastPriority(UserId) + 1;
        
        _context.SaveChanges();
    }

    public void DeleteGroup(GroupsUsersDto dto, int userId)
    {
        _context.GroupsUsers
            .Remove(
            _context.GroupsUsers
                .FirstOrDefault(g => g.GroupId == dto.GroupId && g.UserId == userId)!
        );

        _context.SaveChanges();
    }

    private int GetLastPriority(int userId)
    {
        try
        {
            return _context.GroupsUsers
                .Max(g => g.Priority);
        }
        catch
        {
            return 0;
        }
    }

    private int GetCurrentPriority(int groupId,int userId)
    {
        return _context.GroupsUsers
            .FirstOrDefault(g => g.Users.id == userId && g.Groups.id == groupId)!.Priority;
    }
    
    //тут словарь ключ это id timetable а значение это приоритет этого timeTable
    public void ChangePriority(int userId,int groupId,int currentPriority)
    {
        var groupsUsers = _context.GroupsUsers
            .FirstOrDefault(g => g.GroupId == groupId && g.UserId == userId);
        groupsUsers.Priority = currentPriority;
        _context.SaveChanges();
    }

    public Dictionary<int, int> GetAllPriorities(int userId)
    {
        Dictionary<int, int> priority = new Dictionary<int, int>();

        List<Group> Groups = _context.GroupsUsers
            .Where(u => u.UserId == userId)
            .Select(g=>g.Groups).ToList();
        
        foreach (var group in Groups)
        {
            priority.Add(group.TimeTable.id,GetCurrentPriority(group.id,userId));
        }

        return priority;
    }


    public List<GroupsUsersDto> GetAllCodesByUserId(int id)
    {
        GetAllTimeTables(id);
        Dictionary<int,int> priorities = GetAllPriorities(id);
        List<GroupsUsersDto> codes = new List<GroupsUsersDto>();
    
        IEnumerable<int> groupsId = GetAllGroupIdByUserId(id);
        
        IEnumerable<Group> groups = _context.Groups
            .Include(g=>g.TimeTable)
            .Where(g => groupsId.Contains(g.id));
        
        foreach (var group in groups)
        {
            codes.Add(new GroupsUsersDto
            {
                GroupId = group.id,
                Code = group.Code,
                priority = priorities[group.TimeTable.id]
            });

        }
        
        return codes;
    }

    public List<TimeTable> GetAllTimeTables(int userId)
    {
        List<int> GruopsIds = GetAllGroupIdByUserId(userId);
        List<TimeTable> timeTables = new List<TimeTable>();
        foreach (int groupId in GruopsIds)
        {
            timeTables.Add(GetTimeTableByGroupId(groupId));
        }

        return timeTables;
    }
    
    private TimeTable GetTimeTableByGroupId(int id)
    {
        TimeTable timeTable1 = _context.Groups
            .Include(g=>g.TimeTable)
            .FirstOrDefault(g => g.id == id)!.TimeTable;
        
        timeTable1.Days = _context.Days
            .Where(d => d.timeTableId == timeTable1.id)
            .Include(d=>d.Pairs)
            .ToList();
        
        return timeTable1;
    }
    
    private List<int> GetAllGroupIdByUserId(int id)
    {
        IEnumerable<int> groupsId = _context.GroupsUsers
            .Where(u => u.UserId == id)
            .Select(g=>g.GroupId);

        return groupsId.ToList();
    }
    
    
}