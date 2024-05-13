using BarsantiExplorer.Models.Entities;
using System.ComponentModel.DataAnnotations;

namespace PictionaryParty.Models.Entities
{
    public class Word : BaseEntity
    {
        [MaxLength(100)]
        [Required]
        public string English { get; set; }

        [MaxLength(100)]
        [Required]
        public string Italian { get; set; }

        [MaxLength(100)]
        [Required]
        public string Category { get; set; }

    }
}
