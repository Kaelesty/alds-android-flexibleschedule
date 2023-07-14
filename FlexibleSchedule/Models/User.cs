using Helpers.ScheduleHandler.Interfaces;

namespace Models;

public class User
{
    public int id { get; set; }
    public string name  { get; set; }
    public string email { get; set; }
    public string password { get; set; }
    [System.Text.Json.Serialization.JsonIgnore]

    public List<Group> Groups { get; set; }
    [System.Text.Json.Serialization.JsonIgnore]

    public List<GroupsUsers> GroupsUsers { get; set; }
}