import React from 'react';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContentText from '@material-ui/core/DialogContentText';
import Dialog from '@material-ui/core/Dialog';
import Button from '@material-ui/core/Button';

export function CustomAlertDialog(props) {

const {onClose,title,message} = props;

const [opendialog, setOpenDialog] = React.useState(true);

const handleClose = () => {
	setOpenDialog(false);
	onClose();
};

return (
	<div>
	<Dialog open={opendialog}>
		<DialogTitle>
		 {title}
		</DialogTitle>
		<DialogContent>
		<DialogContentText>
			{message}
		</DialogContentText>
		</DialogContent>
		<DialogActions>
		<Button onClick={handleClose} color="primary">
		 Close
		</Button>
		
		</DialogActions>
	</Dialog>
	</div>
);
}
