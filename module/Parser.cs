using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace module
{
    public class Parser
    {
        public static T FieldDeserialize<T>(string JSON, string field)
        {
            JObject responseObject = JObject.Parse(JSON);
            if (responseObject[field] == null)
                return default;
            return responseObject[field].ToObject<T>();
        }

        public static string RequestSerialize(string operation, object payload)
        {
            return JsonConvert.SerializeObject(new
            {
                operation,
                payload
            });
        }
    }
}
