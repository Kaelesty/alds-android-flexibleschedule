namespace Models;

public class GroupsUsers
{
    public int id { get; set; }
    public int UserId { get; set; }
    public User Users { get; set; }

    public int GroupId { get; set; }
    public Group Groups { get; set; }
}