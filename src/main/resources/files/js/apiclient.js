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
<<<<<<< HEAD
        showWords:function(callback){
=======
        showWords:function(word,callback){
>>>>>>> a12c2930f4721d3b9bfcc5aeeab869cee50a546e
              const get_request = $.ajax({
                  url: "/showWords",
                  type: "GET",
                  contentType: "application/json",
              });
              get_request.then(function(data){
<<<<<<< HEAD
=======
                    console.log(data);
>>>>>>> a12c2930f4721d3b9bfcc5aeeab869cee50a546e
                    callback(data,data);
                }, function(error) {
                    callback(null, null);
                });
          },

	}
})();