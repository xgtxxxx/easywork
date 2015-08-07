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
    	var view = this.getView();
    	var container = view.ownerCt.items.items[1];
    	container.removeAll();
    	var app = Ext.create('app.view.job.JobViewport');
    	container.add(app);
    },

    onClickButton: function () {
        Ext.Msg.confirm('Confirm', 'Are you sure?', 'onConfirm', this);
    },

    onConfirm: function (choice) {
    	if (choice === 'yes') {  
            this.getViewModel().set('name' , "修改后的title");  
        }  
    }
});
