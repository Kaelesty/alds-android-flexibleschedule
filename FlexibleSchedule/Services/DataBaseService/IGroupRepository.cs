using Models;
using WebApplication1.DataTransfersObjects;

namespace FlexibleSchedule.Services.DataBaseService;

public interface IGroupRepository
{
    Group Create(Group group);
    Group GetById(int id);
    void ConnectToGroup(ConnectGroupDto dto,int userId);
    public IEnumerable<IEnumerable<IEnumerable<string>>> GetTimeTableById(int id);

}