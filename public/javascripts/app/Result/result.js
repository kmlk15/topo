$(document).ready(function() {

    var run = function(id, action) {
        $.ajax({
            type    :   'POST',
            datatype:   'json',
            url     :   '/result/'+action,
            data    :   {
                id	:   id
            },
            timeout :   30000,
            success :   function(d) {
            }
        });
    }

    var addToKnapsack = function(id, name) {
    	var item = '<div class="locList" id="list' + id + '">';
    	item += '<div class="locName">' + name + '</div>';
    	item += '<div class="removeCell"><a class="removeBtn" href="#" id="lr' + id + '">Remove</a></div>';
    	item += '<div class="clear"></div>';
    	item += '</div>';
    	$('#knapsack').after(item);
    }
    
    $('a.addBtn').live("click", function(e) {
        e.preventDefault();
        var id = this.id.substring(2);
        var prefix = this.id.substring(0, 1);
        var name = $('#'+prefix+'n'+id).html();
        var item = $('#list'+id);
        if (item.length == 0) {
	        addToKnapsack(id, name);
	        run(id, "add");
        }
    });

    $('a.dismissBtn').live("click", function(e) {
        e.preventDefault();
        var id = this.id.substring(2);
        run(id, "dismiss");
    });

    $('a.removeBtn').live("click", function(e) {
        e.preventDefault();
        var id = this.id.substring(2);
        var prefix = this.id.substring(0, 1);
        var item = $('#list'+id);
        if (item.length > 0) {
        	item.remove();
        	run(id, "remove");
        }
    });
});