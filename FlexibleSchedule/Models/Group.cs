namespace Models;

public class Group
{
    public int id { get; set; }
    public int Code{ get; set; }
    public int TimeTableid { get; set; }
    public List<User> Users { get; set; }
    public List<GroupsUsers> GroupsUsers { get; set; }
}