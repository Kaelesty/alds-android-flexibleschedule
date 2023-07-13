using Helpers.ScheduleHandler.Interfaces;


namespace Models;

public class Group : IGroupScheduleHandler
{
    public int id { get; set; }
    public string Code{ get; set; }
    public TimeTable TimeTable { get; set; }
    public int CreatorId { get; set; }
    [System.Text.Json.Serialization.JsonIgnore]
    public List<User> Users { get; set; }
    [System.Text.Json.Serialization.JsonIgnore]
    public List<GroupsUsers> GroupsUsers { get; set; }
}