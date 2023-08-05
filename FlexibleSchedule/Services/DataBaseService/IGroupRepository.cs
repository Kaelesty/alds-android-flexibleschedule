using Models;
using WebApplication1.DataTransfersObjects;
using Helpers.ScheduleHandler.Interfaces;

namespace FlexibleSchedule.Services.DataBaseService;

public interface IGroupRepository : IGetScheduleRepository
{
    Group Create(Group group);
    Group GetGroupById(int id);
    void ConnectToGroup(ConnectGroupDto dto,int userId);
    public void DeleteGroup(GroupsUsersDto dto, int UserID);
    public List<GroupsUsersDto> GetAllCodesByUserId(int id);

}