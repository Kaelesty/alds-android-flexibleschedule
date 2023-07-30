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
        User user = _context.Users
            .Include(u=>u.Groups)
            .FirstOrDefault(sp=>sp.id==UserId);
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
        User user = _context.Users.FirstOrDefault(sp=>sp.id==group.CreatorId);
        group.Users.Add(user);
        _context.Groups.Add(group);
        group.id = _context.SaveChanges();
        return group;


    }
    

    public Group GetById(int id)
    {
        return _context.Groups.FirstOrDefault(g => g.id == id);
    }

    public IEnumerable<IEnumerable<IEnumerable<string>>> GetGroupTimeTableById(int id)
    {
        TimeTable timeTable = _context.Groups
            .Include(g=>g.TimeTable)
            .FirstOrDefault(g => g.id == id)!.TimeTable;
        
        List<string> days = new List<string>
        {
            timeTable.Day1, timeTable.Day2, timeTable.Day3, timeTable.Day4, timeTable.Day5, timeTable.Day6, 
            timeTable.Day7
        };
        
        List<List<string[]>> returnTimeTable = new List<List<string[]>>();

        int i=0;
        foreach(string day in days){
            returnTimeTable.Add(new List<string[]>());
            string [] pairs = day.Split('/');
            foreach(string pair in pairs){
                string [] pairInfo = pair.Split('&');
                returnTimeTable[i].Add(pairInfo);
            }
            i++;

        }
        return returnTimeTable;
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
    
    
    public User GetUserByUserId(int id)
    {
        return _context.Users
            .Include(u=>u.Groups)
            .FirstOrDefault(u => u.id == id);
    }

    private List<string> SplitIntoPairs(string Day)
    {
         List<string> Pairs = Day.Split('/').ToList();
         return Pairs;
    }

}