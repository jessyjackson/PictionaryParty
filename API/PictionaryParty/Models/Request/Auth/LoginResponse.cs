using PictionaryParty.Models.Response;

namespace PictionaryParty.Models.Request.Auth;

public class LoginResponse
{
    public UserResponse User { get; set; }
    public string Token { get; set; }
}