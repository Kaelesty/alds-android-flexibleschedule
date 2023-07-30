using Models;
using WebApplication1.DataTransfersObjects;
using Helpers.ScheduleHandler.Interfaces;

namespace FlexibleSchedule.Services.DataBaseService;

public interface IGroupRepository : IGetScheduleRepository
{
    Group Create(Group group);
    Group GetById(int id);
    void ConnectToGroup(ConnectGroupDto dto,int userId);
    public User GetUserByUserId(int id);
    public void DeleteGroup(GroupsUsersDto dto, int UserID);
    public List<GroupsUsersDto> GetAllCodesByUserId(int id);

}