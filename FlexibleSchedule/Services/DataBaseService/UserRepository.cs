using Microsoft.EntityFrameworkCore;
using Services.DataBaseService;
using Models;
namespace FlexibleSchedule.Services.DataBaseService;

public class UserRepository : IUserRepository
{
    private IUserRepository _userRepositoryImplementation;
    
    private Context _context;
    
    public UserRepository(Context context)
    {
        _context = context;
    }

    public User Create(User user)
    {
        _context.Users.Add(user);

        user.id = _context.SaveChanges();
        return user;
        
    }

    public User GetByEmail(string email)
    {
        return _context.Users.FirstOrDefault(u => u.email == email);
    }
    
    public User GetById(int id)
    {
        return _context.Users.FirstOrDefault(u => u.id == id);
    }

}