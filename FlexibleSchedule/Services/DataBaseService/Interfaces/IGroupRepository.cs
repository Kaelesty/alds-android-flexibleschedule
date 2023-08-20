using Models;
using WebApplication1.DataTransfersObjects;
using Helpers.ScheduleHandler.Interfaces;

namespace FlexibleSchedule.Services.DataBaseService;

public interface IGroupRepository
{
    void ChangePriority(int userId, int groupId, int currentPriority);

    Dictionary<int, int> GetAllPriorities(int userId);

    Group Create(Group group);
    
    void ConnectToGroup(ConnectGroupDto dto,int userId);
    
    void DeleteGroup(GroupsUsersDto dto, int UserID);
    
    List<GroupsUsersDto> GetAllCodesByUserId(int id);
    
    List<TimeTable> GetAllTimeTables(int UserId);


}