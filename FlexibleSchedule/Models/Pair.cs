using System.Text.Json.Serialization;

namespace Models;

public class Pair
{
    [JsonIgnore]
    public int id { get; set; }
    public string Time { get; set; }
    public string Info { get; set; }
    public string Place { get; set; }
    public string Teacher { get; set; }
}