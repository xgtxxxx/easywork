Ext.define('app.view.job.task.TaskList', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.tasklist',
	requires : ['app.view.job.task.TaskListController'],
	uses : ['app.store.job.TaskStore'],
	controller : 'tasklist',
	initComponent : function() {
		var store = Ext.create('app.store.job.TaskStore');
		this.store = store;
		this.dockedItems = [{
	        xtype : 'pagingtoolbar', // grid数据分页  
            store : this.store,  
            displayInfo : true,  
            prependButtons : true,  
            dock : 'bottom'
	    }];
		this.columns = [ {
			xtype : 'rownumberer'
		}, {
			header : 'Name',
			dataIndex : 'name'
		}, {
			header : 'Clazz',
			dataIndex : 'clazz'
		}, {
			header : 'Description',
			dataIndex : 'description'
		}, {
			header : 'Operate',
			width : 50,
			xtype:'actioncolumn',
	        items: [{
	            iconCls: 'icon-detail', 
	            tooltip: 'Show Detail',
	            handler: 'showDetail'
	        },{
	            iconCls: 'icon-resume', 
	            tooltip: 'Start Now',
	            handler: 'startJobNow'
	        },{
	            iconCls: 'icon-delete', 
	            tooltip: 'Delete job',
	            handler: 'deleteJob'
	        }]
		} ];
		this.tbar = [ {
			 xtype : 'label',
			 text : 'Job List'
			}, '->', {
			text : 'New Job',
			iconCls : 'icon-add',
			handler : "showAddWin"
		}];
		this.callParent(arguments);
		Ext.QuickTips.init();
	}
});
