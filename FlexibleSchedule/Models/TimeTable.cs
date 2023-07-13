using System.ComponentModel.DataAnnotations;

namespace Models;

public class TimeTable
{
    [Key]
    public int id { get; set; }
    public string Pair1{ get; set; }
    public string Pair2{ get; set; }
    public string Pair3{ get; set; }
    public string Pair4{ get; set; }
    public string Pair5{ get; set; }
    public string Pair6{ get; set; }
    public string Pair7{ get; set; }
    
}