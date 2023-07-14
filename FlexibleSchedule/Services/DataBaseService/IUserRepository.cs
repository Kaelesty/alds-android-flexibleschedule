using Models;

namespace FlexibleSchedule.Services.DataBaseService;

public interface IUserRepository
{
    User Create(User user);
    User GetByEmail(string email);
    public User GetById(int id);


}