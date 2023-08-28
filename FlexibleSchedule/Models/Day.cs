using System.Text.Json.Serialization;

namespace Models;

public class Day
{
    public List<Pair> Pairs { get; set; }
    public int timeTableId { get; set; }
    [JsonIgnore]
    public int id { get; set; }

}