using BarsantiExplorer.Models.Entities;
using PictionaryParty.Models.Response;
using System.ComponentModel.DataAnnotations;

namespace PictionaryParty.Models.Entities
{
    public class User : BaseEntity
    {
        [MaxLength(100)]
        [Required]
        public string Email { get; set; }

        [MaxLength(64)]
        [Required]
        public string Password { get; set; }

        public UserResponse MapToUserResponse()
        {
            return new UserResponse
            {
                Email = Email
            };
        }

    }
}
