Ext.define('app.view.job.task.TaskListController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.tasklist',
    
    showAddWin : function(){
    	var grid = this.getView();
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
    },
    showDetail : function(grid, rowIndex, colIndex){
    	var me = this.getView();
		var record = grid.getStore().getAt(rowIndex);
		var layout = me.ownerCt.getLayout();
		var next = me.ownerCt.down('tasktrigger');
		ExtUtil.slideActive(layout,next,'r');
		next.reload(record.data.name);
	},
	deleteJob : function(grid, rowIndex, colIndex){
		var p = this.getView();
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to delete this job : "+record.data.name+"?",function(){
			p.doRequest({jobName:record.data.name},"/job/deleteJob.do")
		})
	},
	startJobNow : function(grid, rowIndex, colIndex){
		var p = this.getView();
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to start this job("+record.data.name+") now?",function(){
			p.doRequest({jobName:record.data.name},"/job/startJobNow.do")
		})
	}
});
