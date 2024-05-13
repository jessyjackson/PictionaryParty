using Microsoft.AspNetCore.Mvc;
using PictionaryParty.Models;

namespace PictionaryParty.Controllers
{
    public class BaseController : Controller
    {
        protected PictionaryPartyDBContext DB;
        protected IConfiguration? AppSettings;
        public BaseController(PictionaryPartyDBContext context)
        {
            DB = context;
        }

        public BaseController(PictionaryPartyDBContext context, IConfiguration appSettings)
        {
            DB = context;
            AppSettings = appSettings;
        }
    }
}
