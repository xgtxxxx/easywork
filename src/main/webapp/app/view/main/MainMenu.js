Ext.define('app.view.main.MainMenu', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.mainmenu',
	xtype : 'mainmenu',
	requires : ['app.view.main.MainController'],
	controller : 'main',
	viewModel : 'main',
	layout : { 
		 // layout-specific configs go here
        type: 'accordion',
        animate: true
    },  
	border : true,
	initComponent : function() {
		this.items = [
			{
		        title: 'Job',
		        xtype : 'panel',
		        bodyStyle : {  
                    padding : '10px'  
                },  
                layout : 'fit', 
                items : [{
                	xtype : 'treepanel',
                	rootVisible : false,
                	store : Ext.create('Ext.data.TreeStore', {
            			root : {
            				expanded : true,
            				children : [{
            					text : "Task",
            					xtype : "taskview",
            					clazz : "app.view.job.TaskView",
            					leaf : true
            				},{
            					text : "History",
            					xtype: "historyview",
            					clazz : "app.view.job.HistoryView",
            					leaf : true
            				}]
            			}
            		}),
            		listeners : [{
            			cellclick : 'switchTabPanel'
            		}]
                }]
		    },{
		        title: 'User',
		        html: 'Panel content!'
		    },{
		        title: 'Email',
		        html: 'Panel content!'
		    }
		]
		this.callParent(arguments);
	},
	listeners : [{
		cellclick : 'switchTabPanel'
	}]
});
