using System.Text.Json.Serialization;

namespace Models;

public class Day
{
    [JsonIgnore]
    public int id { get; set; }
    public List<Pair> Pairs { get; set; }
    public int timeTableId { get; set; }

}