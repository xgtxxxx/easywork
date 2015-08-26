Ext.define('app.view.job.task.TaskList', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.tasklist',
	requires : ['app.view.job.task.TaskListController','app.view.common.AuthurityModel'],
	uses : ['app.store.job.TaskStore'],
	controller : 'tasklist',
	initComponent : function() {
		var me = this;
		this.viewModel = Ext.create('app.view.common.AuthurityModel',{
			mid : me.up('taskview').mid
		});
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
			header : 'Detail',
			width : 30,
			xtype:'actioncolumn',
	        items: [{
	            iconCls: 'icon-detail', 
	            tooltip: 'Show Detail',
	            handler: 'showDetail'
	        }]
		} , {
			header : 'Operate',
			width : 50,
			xtype:'actioncolumn',
			bind : {
				hidden : '{readOnly}'
			},
	        items: [{
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
			handler : "showAddWin",
			bind : {
				hidden : '{readOnly}'
			}
		}];
		this.callParent(arguments);
		Ext.QuickTips.init();
	}
});
