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


    public void ConnectToGroup(ConnectGroupDto dto,int UserId)
    {
        User user = _context.Users.Include(u=>u.Groups).FirstOrDefault(sp=>sp.id==UserId);
        
        user.Groups.Add(_context.Groups.FirstOrDefault(g=>g.Code==dto.Code));
        
        _context.SaveChanges();

    }

    public void DeleteGroup(GroupsUsersDto dto, int UserID)
    {
        _context.GroupsUsers.Remove(
            _context.GroupsUsers.Where(g => g.GroupId == dto.GroupId & g.UserId == UserID).FirstOrDefault()
        );
        _context.SaveChanges();
    }

    public Group Create(Group group) //todo добавить проверку, чтобы пользователь не мог создать больше двух расписаний!!!
    {
        User Creator = _context.Users.FirstOrDefault(sp=>sp.id==group.CreatorId);
        group.Users.Add(Creator);
        _context.Groups.Add(group);
        group.id = _context.SaveChanges();
        return group;


    }
    
    public Group GetGroupById(int id)
    {
        return _context.Groups.FirstOrDefault(g => g.id == id);
    }

    public TimeTable GetTimeTableByGroupId(int id)
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
    public List<GroupsUsersDto> GetAllCodesByUserId(int id)
    {
        List<GroupsUsersDto> Codes = new List<GroupsUsersDto>();
        
        IEnumerable<int> GroupsId = _context.GroupsUsers
            .Where(u => u.UserId == id)
            .Select(g=>g.GroupId);
        
        IEnumerable<Group> Groups = _context.Groups.Where(g => GroupsId.Contains(g.id));
        
        foreach (var group in Groups)
        {
            Codes.Add(new GroupsUsersDto
            {
                GroupId = group.id,
                Code = group.Code
            });

        }
        
        return Codes;
    }
    
    
}