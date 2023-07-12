namespace Models;

public class Group
{
    public int id { get; set; }
    public int Code{ get; set; }
    public TimeTable TimeTable { get; set; }
    
    public List<User> Users { get; set; }
    [System.Text.Json.Serialization.JsonIgnore]
    public List<GroupsUsers> GroupsUsers { get; set; }
}