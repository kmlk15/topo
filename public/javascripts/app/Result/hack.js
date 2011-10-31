$(document).ready(function() {
    $('div.contentImg').each(function() {
    	var items = $(this).find('div.imgHeader');
    	var height = 0;
    	items.each(function() {
    		var h = $(this).height();
    		height = (h > height) ? h : height;
    	});
    	items.each(function() {
    		$(this).height(height);
    	});
    });
        
});