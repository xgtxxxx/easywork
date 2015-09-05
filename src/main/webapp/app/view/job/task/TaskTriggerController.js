Ext.define('app.view.job.task.TaskTriggerController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.tasktrigger',
    
	goBack : function(){
		var me = this.getView();
		var layout = me.ownerCt.getLayout();
		var next = me.ownerCt.down('tasklist');
		ExtUtil.slideActive(layout,next,'l');
	},
	pauseAll : function(){
		var grid = this.getView();
		grid.confirm("Sure to pause all?",function(){
			var jobName = grid.getJobname();
			grid.doRequest({jobName:jobName},"/job/pauseJob.do")
		})
	},
	resumeAll : function(){
		var grid = this.getView();
		grid.confirm("Sure to resume all?",function(){
			var jobName = grid.getJobname();
			grid.doRequest({jobName:jobName},"/job/resumeJob.do")
		})
	},
	newTrigger : function(){
		var grid = this.getView();
		var jobName = grid.getJobname();
		var form = Ext.create('app.view.job.task.TriggerForm');
		var win = Ext.create("Ext.window.Window",{
			width : 400,
			height : 240,
			title : 'New Trigger',
			layout : 'fit',
			frame : false,
			modal : true,
			items : [form],
			buttonAlign : 'center',
			buttons : [{
				text : 'Save',
				handler : function(){
					var form = win.down('triggerform').getForm();
					if (form.isValid()) {
		                form.submit({
		                	url: CTX.PATH+'/job/newTrigger.do',
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
		win.down('triggerform').setJobName(jobName);
		win.show();
	},
	pauseTrigger : function(grid, rowIndex, colIndex){
		var p = this.getView();
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to pause "+record.data.name+"?",function(){
			p.doRequest({triggerName:record.data.name},"/job/pauseTrigger.do")
		})
	},
	resumeTrigger : function(grid, rowIndex, colIndex){
		var p = this.getView();
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to resume "+record.data.name+"?",function(){
			p.doRequest({triggerName:record.data.name},"/job/resumeTrigger.do")
		})
	},
	deleteTrigger : function(grid, rowIndex, colIndex){
		var p = this.getView();
		var record = grid.getStore().getAt(rowIndex);
		p.confirm("Sure to remove "+record.data.name+"?",function(){
			p.doRequest({triggerName:record.data.name},"/job/removeTrigger.do")
		})
	}
});
