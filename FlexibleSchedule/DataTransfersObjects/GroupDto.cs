using Models;

namespace WebApplication1.DataTransfersObjects;

public class GroupDto
{
    public string Code{ get; set; }
    public TimeTable TimeTable { get; set; }
}

public class ConnectGroupDto
{
    public string Code{ get; set; }
}