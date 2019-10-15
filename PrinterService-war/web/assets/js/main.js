
$(window).bind('load resize scroll', function(){
  var valScroll = $(window).scrollTop();
  var winHeight = $(window).innerHeight();
  var winWidth = $(window).innerWidth();
  
  if(winWidth>1024){
    $('.img_parallax').css('transform','translateY('+valScroll*0.7+'px)');
    if($('.main_content_filter').size()>0){
    	var filterTop = $('.filter_wrapper').offset().top;
    	var contentHeight = $('.content_results').height();
    	var contentTop = $('.content_results').offset().top;
    	var contentEnd = contentHeight+contentTop;
    	var filterHeight = $('.main_content_filter').height();
    	if(valScroll >= filterTop-100 && valScroll < contentEnd-(filterHeight+100)){
    		$('.main_content_filter').css('margin-top',''+valScroll-(filterTop-100)+'px');
    		//$('.gradient_scroll').fadeIn();
    	}else if(valScroll < filterTop-100){
    		$('.main_content_filter').css('margin-top','0');
    		//$('.gradient_scroll').fadeOut();
    	}else{
    		//$('.gradient_scroll').fadeOut();
    	}
    }
  }
  


  $('.to_view').each(function(index, element) {
    var thisTop = $(this).offset().top;
    if(valScroll>= thisTop-(winHeight)){
      $(this).addClass('visible');
    }
  });

  if(valScroll > 50){
    $('header').addClass('scroll_header');
  }else{
    $('header').removeClass('scroll_header');
  }

  /*--$('.content_results .item_event').each(function(){
  	var $this = $(this)
  	var thisTop = $this.offset().top;
  	var thisHeight = 
  });--*/
})

function moveLeft(){
	
}

$(function(){
	if($('section.home').size()>0){
		$('.form_search').remove();
	}
	$('.content_results .item_event').each(function(){
		var $this = $(this);
		var range = 428;

		$(window).on('scroll', function () {
		  
		    var scrollTop = $(window).scrollTop();
		    var offset = $this.offset().top;
		    var height = $this.outerHeight();
		    offset = offset + height / 2;
		    var calc = 1 - ((scrollTop-40) - offset + range) / range;
		  
		    $this.css({ 'opacity': calc });
		  
		    if ( calc > '1' ) {
		      $this.css({ 'opacity': 1 });
		    } else if ( calc < '0' ) {
		      $this.css({ 'opacity': 0 });
		    }
		  
		});
	})
	$('.type_of_repeat').change(function(){
		var thisForm = $(this).find(':selected').attr('data-form');
		console.log(thisForm);
		$('.content_form_repeat').hide();
		$('#'+thisForm+'').show();
	})
	$('.itemCalificationStars.active .fa').mouseenter(function(){
		$(this).parent('span').prevAll().find('.fa').removeClass('fa-star-o').addClass('fa-star')
		$(this).removeClass('fa-star-o').addClass('fa-star')
	})
	$('.itemCalificationStars.active .fa').mouseleave(function(){
		$(this).parent('span:not(.active)').prevAll(':not(.active)').find('.fa').removeClass('fa-star').addClass('fa-star-o');
		if($(this).parent().hasClass('active')){

		}else{
			$(this).removeClass('fa-star').addClass('fa-star-o')
		}
		
	});

	$('.itemCalificationStars.active .fa').click(function(){
		if($(this).parent().hasClass('active')){
			$(this).removeClass('fa-star-o').addClass('fa-star');
			$(this).parent().removeClass('active');
			$(this).parent().nextAll().removeClass('active');
			$(this).parent().nextAll().find('.fa').removeClass('fa-star').addClass('fa-star-o')
		}else{
			$(this).parent('span').prevAll().find('.fa').removeClass('fa-star-o').addClass('fa-star')
			$(this).removeClass('fa-star-o').addClass('fa-star');
			$(this).parent('span').addClass('active');
			$(this).parent('span').prevAll().addClass('active');
		}
		

	})
	$('.content_placeholder input, .content_placeholder textarea').keydown(function(){
		var $this = $(this)
		setTimeout(function(){
			var thisVal = $this.val();
			if(thisVal == ''){
				$this.siblings('.placeholder_form').show();
			}else{
				$this.siblings('.placeholder_form').hide();
			}
		},10)
		
	});


	$('.subnav_item li a').click(function(){
		var $this = $(this);
		var thisText = $(this).text();
		var thisId = $(this).attr('id');
		if($(this).hasClass('active')){
			$(this).removeClass('active');
			$(this).closest('.main_filter_item').find('.item_Selected').each(function(){
				if($(this).attr('data-id') == thisId){
					$(this).remove();
				}
			})
		}else{
			$(this).addClass('active')
			$(this).closest('.main_filter_item').find('.selected_items').append('<div class="item_Selected" data-id="'+thisId+'"><span class="selected_text">'+thisText+'</span> <span class="delete_selected">x</span></div>');
		}
		setTimeout(function(){
			if($this.closest('.subnav_item').find('.active').size() > 0){
				$this.closest('.main_filter_item').addClass('filter_active');
			}else{
				$this.closest('.main_filter_item').removeClass('filter_active');
			}
		},20)
	});

	$('.main_filter_btn').click(function(){
		if($(this).hasClass('disabled')){

		}else{
			if($(this).hasClass('open')){
				$(this).siblings('.subnav_item').hide();
				$('.filter_layer').stop().fadeOut();
				$(this).removeClass('open')
			}else{
				$('.subnav_item').hide();
				$(this).siblings('.subnav_item').show();
				$('.filter_layer').stop().fadeIn();
				$('.main_filter_btn').removeClass('open')
				$(this).addClass('open')
			}
		}
		
	});

	$('.filter_layer').click(function(){
		$('.subnav_item').hide();
		$('.main_filter_btn').removeClass('open');
		$(this).fadeOut();
	});

	$('.item_filter_cat .subnav_item li a').click(function(){
		var $this = $(this);
		setTimeout(function(){
			if($this.closest('.subnav_item').find('.active').size() > 0){
				$this.closest('.main_filter_item').next().find('.main_filter_btn').removeClass('disabled');
			}else{
				$this.closest('.main_filter_item').next().find('.main_filter_btn').addClass('disabled');
			}
		},20)
	})
	$('#filterDate').datetimepicker({
	 	format:'DD/MM/YYYY',
	 	widgetPositioning: {
	      horizontal: 'left',
	      vertical: 'bottom'
	   }
	});

	$("#filterDate").on("dp.hide", function (e) {
		var currentDate = $('.filter_date').val();
		if ($('.item_Selected .selected_text:contains("'+currentDate+'")').size() < 1) {
		    $('.selected_items.selected_dates').append('<div class="item_Selected"><span class="selected_text">'+currentDate+'</span> <span class="delete_selected">x</span></div>');
		}
		
		setTimeout(function(){
			if($('.filter_date').val() != ''){
				$('.content_filter_date').addClass('filter_active');
			}else{
				$('.content_filter_date').removeClass('filter_active');
			}
		},20);
		$('.filter_layer').fadeOut();
		$('.btn_filter_date').removeClass('open')
  });

	$('.btn_filter_date').click(function(){
		$(this).siblings('.filter_date').trigger('focus');
	});

	$('html').on('click','.delete_selected', function(){
		var thisId = $(this).parent().attr('data-id');
		var $this = $(this)
		$('#'+thisId+'').removeClass('active');
		if($(this).closest('.selected_items').siblings('.filter_date').size()>0){
			$(this).closest('.selected_items').siblings('.filter_date').val('');
		}
		
		setTimeout(function(){
			
			if($this.closest('.selected_items').find('.item_Selected').size() < 2){
				$this.closest('.main_filter_item').removeClass('filter_active');
			}
			$this.parent().remove();
			
		},20)
		
	})




	$('#datatable').DataTable({
		paging: false
	});
	$('.btn_delete_field').click(function(){
		$(this).siblings('input').val('')
	})
	$(".autogrow").autogrow();
	$('.slider_planes').owlCarousel({
	    margin:20,
	    loop:true,
	    autoWidth:true,
	    items:7,
	    nav:true,
	    center:true,
	    startPosition:1

	})
	$('.like').click(function(event){
		event.preventDefault();
		$(this).toggleClass('active')
	})
	$('[data-toggle="tooltip"]').tooltip()
	$('.btn_destacar').click(function(){
		$(this).toggleClass('active')
	})
	$('html').on('click', '.tag span', function(){
		console.log('remove')
	})
	$(".input_tags").tagsinput();
	$('.content_tags').each(function(){
		if($(this).find('.tag').size()>0){
			$(this).find('.placeholder_form').hide();
		}
		$(this).find('input').keydown(function(){
			var $this = $(this)
			setTimeout(function(){
				var thisVal = $this.val();
				if(thisVal == ''){
					if($this.closest('.content_tags').find('.tag').size()<1){
						$this.closest('.content_tags').find('.placeholder_form').show();
					}
					
				}else{
					$this.closest('.content_tags').find('.placeholder_form').hide();
				}
			},10)
		});

		
	})

	$('input').on('itemRemoved', function(event) {
	  if($(this).closest('.content_tags').find('.tag').size()<1){
	  	$(this).closest('.content_tags').find('.placeholder_form').show();
	  }
	});

  $('.btn_file input').change(function(){
    var thisVal = $(this).val().replace(/C:\\fakepath\\/i, '');
    if(thisVal== ''){
      $(this).siblings('.btn').children('span').text('ADJUNTAR PROGRAMACIÓN');
    }else{
      $(this).siblings('.btn').children('span').text(thisVal);
    }
    
  })

  $('.btn-tooltip, html, body').click(function(){
		$('.tooltip_repeat').fadeOut();
	});

	$('.btn_select_repeat').click(function(event){
		event.stopPropagation();
		$(this).siblings('.tooltip_repeat').fadeIn();
	})
	

	$('.tooltip_repeat').click(function(event){
		event.stopPropagation();
	})

	
	$('.select_img1').click(function(){
		$(this).siblings('.cropit-image-input').click();
		$('#image-cropper').cropit({
			smallImage:'stretch'
		});
	})
	$('.cropit-image-input').change(function(){
		$(this).siblings('.cropit-image-zoom-input').show();
	})
	$('.select_img2').click(function(){
		$(this).siblings('.cropit-image-input').click();
		$(this).closest('.image-cropper2').cropit();
	})
	 $('.picker_input').datetimepicker({
	 	format:'DD/MM/YYYY'
	 });
	 $('.picker_input2').datetimepicker({
	 	format:'hh:mm'
	 });
	 $('.bxslider').bxSlider();
	  $('#myTabs a').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		})
	$('.slider_destacados').each(function(){
    if( $(this).find(".item").length < 5 ) {
      $(this).owlCarousel({
        items: 4,
        responsive:{
          0:{
            items:1
          },600:{
            items:2
          },800:{
            items:3
          },1000:{
            items:4
          }
        }
      });
    }else{
      $(this).owlCarousel({
        loop: true,
        margin: 20,
        nav: true,
        items: 4,
        responsive:{
          0:{
            items:1
          },600:{
            items:2
          },800:{
            items:3
          },1000:{
            items:4
          }
        },
        autoplay:false
      });
    }
  });

  $('.slider_sponsors').each(function(){
    if( $(this).find(".item").length < 5 ) {
      $(this).owlCarousel({
        items: 4,
        responsive:{
          0:{
            items:3
          },600:{
            items:4
          }
        }
      });
    }else{
      $(this).owlCarousel({
        loop: true,
        margin: 20,
        nav: false,
        items: 4,
        responsive:{
          0:{
            items:3
          },600:{
            items:4
          }
        },
        autoplay:true
      });
    }
  });

  $('.slider_int').each(function(){
    if( $(this).find(".slider_img").length < 2 ) {
      $(this).owlCarousel({
        items: 1
      });
    }else{
      $(this).owlCarousel({
        loop: true,
        margin: 0,
        nav: false,
        items: 1,
        autoplay:true
      });
    }
  });

  $('.slider_editorial').each(function(){
    if( $(this).find(".item").length < 2 ) {
      $(this).owlCarousel({
        items: 1
      });
    }else{
      $(this).owlCarousel({
        loop: true,
        margin: 0,
        nav: true,
        items: 1,
        autoplay:true
      });
    }
  });
	$('#datetimepicker').datetimepicker({
      inline: true,
      defaultDate: "2/1/2017",
      enabledDates: [ 
        "2/20/2017",
        "2/21/2017"
      ]
  });
	$('.btn_menu').click(function(){
		if($('.menu_header').is(':hidden')){
			$('.menu_header').fadeIn();
			$(this).addClass('open')	
		}else{
			$('.menu_header').fadeOut();
			$(this).removeClass('open')
		}
		

	})

	$('.btn_session').click(function(){
		if($('.menu_session').is(':hidden')){
			$('.menu_session').fadeIn();
			$(this).addClass('open')	
		}else{
			$('.menu_session').fadeOut();
			$(this).removeClass('open')
		}
		

	})
	var eventsSize = $('.event_col').size();
	var eventsWidth = $('.event_col').innerWidth();
	var contentWidth = eventsSize*eventsWidth;
	var winWidth = $(window).innerWidth();
	$('.events_container').width(contentWidth);
	$( "#slider" ).slider({
		max:contentWidth - winWidth,
		min:0
	});

	$( "#slider" ).on( "slidechange slide", function( event, ui ) {
		var val = $('#slider').slider("option", "value");
		var max = $('#slider').slider("option", "max");
		
		var val2 = val - winWidth
		$('.events_viewport').scrollLeft(val);

		console.log(max)
	});
	$('.events_viewport').scroll(function(){
		var thisLeft = $(this).scrollLeft();
		$('#slider').slider('value',thisLeft);
		console.log(thisLeft)
	})


	$('#startSesion').on('click',function(e){
		e.preventDefault();
		$('#modal_login').fadeIn();
	});

	$('#signup').on('click',function(e){
		e.preventDefault();
		$('#modal_signup').fadeIn();
	});

	$('.modalContentLightBox').click(function(){
		$('.modalContentLightBox').fadeOut();
	});

	$('.lightBoxStartSesion').click(function(event){
		event.stopPropagation();
	})
	

	$('#slideToUs').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('article.about_us').offset().top
		},1000);
		e.preventDefault();
	});
	$('#slideToPortfolio').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('article.portfolio').offset().top
		},1000);
		e.preventDefault();
	});
	$('#slideToContact').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('.contactanos').offset().top
		},1000);
		e.preventDefault();
	});


	$('#generadores').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('.generador').offset().top - 110+'px'
		},1000);
		e.preventDefault();
	});
	$('#contratistas').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('.contratistas').offset().top - 110+'px'
		},1000);
		e.preventDefault();
	});
	$('#distribuidores').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('.distribuidores').offset().top - 110+'px'
		},1000);
		e.preventDefault();
	});

	$('#gen').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('.generador').offset().top - 110+'px'
		},1000);
		e.preventDefault();
	});
	$('#cont').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('.contratistas').offset().top - 110+'px'
		},1000);
		e.preventDefault();
	});
	$('#dist').on('click',function(e){
		$('body, html').animate({
			'scrollTop': $('.distribuidores').offset().top - 110+'px'
		},1000);
		e.preventDefault();
	});

});

