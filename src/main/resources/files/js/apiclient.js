apiclient=(function(){

	return {
		addWord:function(word){
		    console.log(word);
            const put_request = $.ajax({
                url: "/addWord",
                type: "POST",
                data: '{"word":'+word+'}',
                contentType: "application/json",
            });
        },
        showWords:function(callback){
              const get_request = $.ajax({
                  url: "/showWords",
                  type: "GET",
                  contentType: "application/json",
              });
              get_request.then(function(data){
                    callback(data,data);
                }, function(error) {
                    callback(null, null);
                });
          },

	}
})();