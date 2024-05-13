namespace PictionaryParty.Models.Request.Words
{
    public class GetWordsRequest
    {
        public string ?Language { get; set; }
        public string ?Category { get; set; }
    }
}
