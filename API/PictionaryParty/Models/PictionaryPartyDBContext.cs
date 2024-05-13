using Microsoft.EntityFrameworkCore;
using PictionaryParty.Models.Entities;

namespace PictionaryParty.Models
{
    public class PictionaryPartyDBContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<Word> Words { get; set; }

        public PictionaryPartyDBContext(DbContextOptions<PictionaryPartyDBContext> options) : base(options)
        {
        }
    }
}
