var apiclient = (function(){

    return {
    	addWord: function(word, callback) {
            var promise = $.post({
        		url: "/add",
        		data: JSON.stringify(word),
        		contentType: "application/json"
            });
        	promise.then(function(data){
        		callback(null, JSON.parse(data));
        	}, function(error) {
        		callback(error, null);
        	});
        }
    }
})();