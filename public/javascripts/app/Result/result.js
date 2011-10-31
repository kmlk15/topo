$(document).ready(function() {

    var add = function(id) {
        $.ajax({
            type    :   'POST',
            datatype:   'json',
            url     :   '/result/add',
            data    :   {
                id	:   id
            },
            timeout :   30000,
            success :   function(d) {
            }
        });
    }

    var remove = function(id) {
        $.ajax({
            type    :   'POST',
            datatype:   'json',
            url     :   '/result/remove',
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
    	item += '<div class="removeCell"><a class="removeBtn" href="#">Remove</a></div>'
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
	        add(id);
        }
    });

    $('a.dismissBtn').live("click", function(e) {
        e.preventDefault();
        var id = this.id.substring(2);
        var prefix = this.id.substring(0, 1);
        var item = $('#list'+id);
        if (item.length > 0) {
        	item.remove();
        	remove(id);
        }
    });

});