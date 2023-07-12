namespace Models;

public class User
{
    public int id { get; set; }
    public string name  { get; set; }
    public string email { get; set; }
    public string password { get; set; }
    public List<Group> Groups { get; set; }
    public List<GroupsUsers> GroupsUsers { get; set; }
}