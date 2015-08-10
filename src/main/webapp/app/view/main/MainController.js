Ext.define('app.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    requires: [
        'Ext.window.MessageBox'
    ],
    
    alias: 'controller.main',
    
    onSysmenuClick : function(btn,e){
    	var btns = btn.ownerCt.items.items;
    	Ext.Array.each(btns, function(menu) { 
    		if(menu.type && menu.type=='menu'){
    			if(menu.pressed){
    				menu.setPressed(false);
    			}
    		}
    	});
    	btn.setPressed(true);
    	btn.addCls("menu-active");
    	var view = this.getView();
    	var container = Ext.getCmp('main-content');
    	container.removeAll();
    	var app = Ext.create('app.view.job.JobViewport');
    	container.add(app);
    	btn.removeCls("x-focus");
    	btn.removeCls("x-btn-focus");
    },
    
    loadHome : function(){
    	var home = Ext.getCmp('home-menu');
    	home.fireEvent('click',home);
    }
});
