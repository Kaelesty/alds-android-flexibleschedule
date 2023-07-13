using System.ComponentModel.DataAnnotations;

namespace Models;

public class TimeTable
{
    [Key]
    public int id { get; set; }
    public string Day1{ get; set; }
    public string Day2{ get; set; }
    public string Day3{ get; set; }
    public string Day4{ get; set; }
    public string Day5{ get; set; }
    public string Day6{ get; set; }
    public string Day7{ get; set; }
    
}