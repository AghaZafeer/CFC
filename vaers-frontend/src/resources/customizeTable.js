import React from "react";
import Table  from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import Button from "@material-ui/core/Button";
import { withStyles } from "@material-ui/core";
const CustomTableCell = withStyles(theme => ({
    head: {
      backgroundColor: theme.palette.primary.main,
      color: theme.palette.common.white,
      fontSize:16
    },
    body: {
      fontSize: 14,
    },
  }))(TableCell);
class CustomizeTable extends React.Component {
  constructor(props) {
    super(props);
    console.log(props); 
    this.state = {items : this.props.prevState};
  }
 deleteItem(i) {
    const { items } = this.state;
    items.splice(i, 1);
    this.setState({ items });
    this.props.parentCallback(items);
  }

  render() {
    const { classes } = this.props;
    return (
      <Paper className={classes.root}>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              <CustomTableCell>Name of the Adverse Event</CustomTableCell>
              <CustomTableCell>Date of the Adverse Event</CustomTableCell>
              <CustomTableCell>Severity of the Adverse Event</CustomTableCell>
              <CustomTableCell>Actions</CustomTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.items.map((item, i) => {
                return (
                    <TableRow key={`row-${i}`}>
                    <TableCell>{item.adverseEffectID}</TableCell>
                    <TableCell>{item.adverseEffectReportingDate}</TableCell>
                    <TableCell>{item.severityID}</TableCell>
                    <TableCell>
                        <Button
                        onClick={this.deleteItem.bind(this, i)}
                        color="primary"
                        >
                        Delete
                        </Button>
                    </TableCell>
                    </TableRow>
                );
            })}
          </TableBody>
        </Table>
      </Paper>
    );
  }
}
export default CustomizeTable;