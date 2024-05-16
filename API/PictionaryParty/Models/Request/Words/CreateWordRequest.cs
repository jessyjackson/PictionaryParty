using System.ComponentModel.DataAnnotations;

namespace PictionaryParty.Models.Request.Words
{
    public class CreateWordRequest
    {
        [MaxLength(100)][Required] public string Category { get; set; }
        [MaxLength(100)][Required] public string English { get; set; }
        [MaxLength(100)][Required] public string Italian { get; set; }
    }
}
