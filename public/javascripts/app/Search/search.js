$(document).ready(function() {

	var i = 0;
	
	var change = function() {
		var current = home.users[i];
		$('div.schFrame').css("background-image", "url("+current.background+")");
		$('div.schUserDesc').html(current.description);
		$('div.schUserImg img').attr("src", current.avatar);
		$('div.schUserCredit p').html(current.credit);
    };
	
	var timer = null;
	
	var resetTimer = function() {
		clearInterval(timer);
		timer = setInterval(function() {
			i++;
	        if (i > 2) {
	        	i = 0;
	        }
	        change();
		}, 20000);
	}
	
	
	$('a.schBtnBack').live("click", function(e) {
        e.preventDefault();
        i--;
        if (i < 0) {
        	i = 2;
        }
        change();
    });

	$('a.schBtnNxt').live("click", function(e) {
        e.preventDefault();
        i++;
        if (i > 2) {
        	i = 0;
        }
        change();
    });

	//resetTimer();

});