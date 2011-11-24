/**
*	Easy Expand jQuery Plugin v 1.0 Copyright 2011 Neil Girardi
*
*	This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
(function( $ ){
  $.fn.easyexpand = function(options, callback) {
  
	return this.each(function() {
  
		var settings = {
		  'startClosed' : true,
		  'outerContainer' : '.outer-container',
		  'buttonContainer' : '.button-container',
		  'buttonClass' : 'Xpandable',
		  'duration' : 200,
		  'easing' : 'swing'
		};
		
		if($.isFunction(options)){
			callback = options;
			options = null;
		}

		if (options) {
			if($.isFunction(options)){
				callback = options;
				options = null;
			}
			$.extend(settings, options);
		}

		var selectorClass = $(this).attr('class');
		var container = '.' + selectorClass;
		
			$(this).closest(settings.outerContainer)
			.find(settings.buttonContainer)
			.append('<a class="' + settings.buttonClass + '" href="#"></a>');

		if (settings.startClosed == false) {
			$(this).closest(settings.outerContainer)
			.find(settings.buttonContainer)
			.addClass('bc-active')
			.find('.' + settings.buttonClass)
			.addClass('link-active')
			.closest(settings.buttonContainer)
			.siblings(container)
			.addClass('content-active');
		}

		if (settings.startClosed == true){
			$(this).hide();
		}
		
		$(this).closest(settings.outerContainer)
		.find('a.' + settings.buttonClass)
		.click(function () {
			$(this)
			.toggleClass('link-active')
			.closest(settings.buttonContainer)
			.toggleClass('bc-active')
			.siblings(container)
			.toggleClass('content-active')
			.stop(true, true)
			.slideToggle(settings.duration, settings.easing);
			$.isFunction(callback) && callback();
			return false;
		});
	});
  };
 })( jQuery );