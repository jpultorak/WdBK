import {
  Box,
  Container,
  Typography,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
} from '@mui/material';
import NavigationBar from '../components/NavigationBar';
import { useGetUserInfoQuery } from '../services/apiSlice';

const transactions = [
  { id: 1, date: '2024-05-01', description: 'Shopping', amount: 1000 },
  { id: 2, date: '2024-05-02', description: 'Grocery Shopping', amount: -100 },
  { id: 3, date: '2024-05-03', description: 'Movie Tickets', amount: -30 },
];

export default function Dashboard() {
  const { data, isLoading } = useGetUserInfoQuery();

  console.log(data);
  return (
    <>
      <NavigationBar />
      <Container maxWidth="md" sx={{ mt: 5 }}>
        <Box
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            mb: 3,
          }}
        >
          {!data || isLoading ? (
            <>Loading...</>
          ) : (
            <>
              <Typography variant="h4" gutterBottom>
                {`Hello, ${data.firstName} ${data.lastName}!`}
              </Typography>
              <Typography variant="h6" color="text.secondary">
                Your current balance is: ${data?.balance} PLN
              </Typography>
            </>
          )}
        </Box>

        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell sx={{ color: 'primary.main' }}>Date</TableCell>
                <TableCell sx={{ color: 'primary.main' }}>
                  Transaction
                </TableCell>
                <TableCell sx={{ color: 'primary.main' }} align="right">
                  Amount (PLN)
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {transactions.map((transaction) => (
                <TableRow key={transaction.id}>
                  <TableCell>{transaction.date}</TableCell>
                  <TableCell>{transaction.description}</TableCell>
                  <TableCell align="right">
                    {transaction.amount.toFixed(2)}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    </>
  );
}
