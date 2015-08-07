Ext.define('app.view.job.task.TaskList', {
	extend : 'app.view.common.OperateGrid',
	alias : 'widget.tasklist',
	initComponent : function() {
		var store = Ext.create('app.store.job.TaskStore');
		this.store = store;
		this.dockedItems = [{
	        xtype: 'pagingtoolbar',
	        store: store, 
	        dock: 'bottom',
	        displayInfo: true
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
	            scope  : this,
	            handler: this.showDetail
	        },{
	            iconCls: 'icon-resume', 
	            tooltip: 'Start Now',
	            scope  : this,
	            handler: this.startJobNow
	        },{
	            iconCls: 'icon-delete', 
	            tooltip: 'Delete job',
	            scope  : this,
	            handler: this.deleteJob
	        }]
		} ];
		this.tbar = [ {
			 xtype : 'label',
			 text : 'Job List'
			}, '->', {
			text : 'New Job',
			iconCls : 'icon-add',
			scope : this,
			handler : this.showAddWin
		}];
		this.callParent(arguments);
		Ext.QuickTips.init();
	},
	showDetail : function(grid, rowIndex, colIndex){
		var record = grid.getStore().getAt(rowIndex);
		var layout = this.ownerCt.getLayout();
		var next = this.ownerCt.down('tasktrigger');
		ExtUtil.slideActive(layout,next,'r');
		next.reload(record.data.name);
	},
	deleteJob : function(grid, rowIndex, colIndex){
		var p = this;
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to delete this job : "+record.data.name+"?",function(){
			p.doRequest({jobName:record.data.name},"/job/deleteJob.do")
		})
	},
	startJobNow : function(grid, rowIndex, colIndex){
		var p = this;
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to start this job("+record.data.name+") now?",function(){
			p.doRequest({jobName:record.data.name},"/job/startJobNow.do")
		})
	},
	showAddWin : function(){
		var grid = this;
		var form = Ext.create('app.view.job.task.JobForm',{
			bodyPadding : '10 0 0 0',
			border: false
		});
		var win = Ext.create("Ext.window.Window",{
			width : 400,
			height : 200,
			title : 'New Job',
			layout : 'fit',
			frame : false,
			modal : true,
			items : [form],
			buttonAlign : 'center',
			buttons : [{
				text : 'Save',
				handler : function(){
					var form = win.down('jobform').getForm();
					if (form.isValid()) {
		                form.submit({
		                	url: CTX.PATH+'/job/newJob.do',
		                    success: function(form, action) {
		                       Ext.Msg.alert('Success', action.result.message);
		                       grid.getStore().reload();
		                       win.close();
		                    },
		                    failure: function(form, action) {
		                        Ext.Msg.alert('Failed', action.result ? action.result.message : 'No response');
		                    }
		                });
		            }
				}
			},{
				text : 'Cancel',
				handler : function(){
					win.close();
				}
			}]
		});
		win.show();
	}
});
