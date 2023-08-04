using System.ComponentModel.DataAnnotations;
using System.Diagnostics.CodeAnalysis;

namespace Models;

public class TimeTable
{
    [Key]
    public int id { get; set; }

    public List<Day> Days { get; set; }

}