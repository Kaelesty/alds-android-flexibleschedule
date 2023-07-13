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
}