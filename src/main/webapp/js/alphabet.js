var Page = {
	audio: {},
	preLoadMedia: function (){
		$(".letter").each(function(idx, val){
	        var wavName = $(val).data("wav");
	        if(!audio[wavName]){
	            audio[wavName] = new Audio();
	            audio[wavName].src = "../media/letters2/"+wavName;
	        }
	    });
	},
	appendHandlers:function(){
		$(".letter").each(function(idx, val){
	        $(val).bind("click", function(){
	            var wavName = $(val).data("wav");
	            if(!audio[wavName]){
	                alert('Failed to load audiofile');
	            }
	            audio[wavName].play();
	        });
	    });	
	}
};

$(document).ready(function(){
    audio = {};
    $(".letter").each(function(idx, val){
        $(val).bind("click", function(){
            var wavName = $(val).data("wav");
            if(!audio[wavName]){
                audio[wavName] = new Audio();
                audio[wavName].src = "../media/letters2/"+wavName;
            }
            audio[wavName].play();
        });
    });
})