function AjaxLoadingBar(){
	var loadingMessage = '';
	var errorMessage = '';
	var activeAjaxRequestsCount = 0;
	var bar = this;

	this.init = function(loadingMessage, errorMessage){
		this.loadingMessage = loadingMessage;
		this.errorMessage = errorMessage;
		
		if(jQuery("#ajaxLoading").size()<1){
			jQuery('body').append('<div id="ajaxLoading" class="ajaxLoading" width="100%">'+loadingMessage+'</div>');
		}
		this.activeAjaxRequestsCount = 0;

		jQuery(document).ajaxComplete(function(){
			bar.hideAjaxLoadingMessage();
		});

		jQuery(document).ajaxSend(function(){
			bar.showAjaxLoadingMessage();
		});

		jQuery(document).ajaxError(function(event, request, settings ){
			if(request.responseJSON && request.responseJSON.errorMessages){
				var message = "";
				for(i = 0; i < request.responseJSON.errorMessages.length; i++){
					message += "\n"+request.responseJSON.errorMessages[i];
				}
				alert(message);
			}else{
				alert('Unknown error');
			}
			bar.hideAjaxLoadingMessage();
		});
	}

	this.isAjaxCompleted = function(){
		return this.activeAjaxRequestsCount==0;
	}
	
	this.showAjaxLoadingMessage = function(){
		jQuery('#ajaxLoading').css('visibility', 'visible');
		this.activeAjaxRequestsCount++;
	}

	this.hideAjaxLoadingMessage = function(){
		if(1>=activeAjaxRequestsCount){
			jQuery('#ajaxLoading').css('visibility', 'hidden');
			this.activeAjaxRequestsCount=0;
		}else{
			this.activeAjaxRequestsCount--;
		}
	}
}