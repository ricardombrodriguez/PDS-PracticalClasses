public class Galinha implements JGaloInterface {
    private Boolean grid[][]; // este é o nosso jogo null = vazio, 0 = bolas, 1 = cruz?
    private Boolean turn;

    public Galinha() {
        this.grid = new Boolean[3][3];
        this.turn = false;
    }

    @Override
    public char getActualPlayer() {
        //Alternates between players?
        if (this.turn) {
            this.turn = false;
            return 'X';
        } else {
            this.turn = true;
            return 'O';
        }
    }

    @Override
    public boolean setJogada(int lin, int col) {
        this.grid[lin-1][col-1] = this.turn;// im not sure tho lets try (googling 1 sec)
        return false;
    }

    @Override
    public boolean isFinished() {
        if(checkResult() != 0 ) return true;
        else {
            int count = 0;
            for(Boolean[] col : grid) {
                for(Boolean b: col) {
                    if (b != null) count++;
                }
            }
            return count == 9;
        }
    }

    @Override
    public char checkResult() {
        //check cols
        for (int i = 0; i < 3; i++) { // para cada linha
            if(this.grid[i][0]!=null&&this.grid[i][1]!=null && this.grid[i][2]!=null) {// se os valores da linha não estiverem vazios
                if (this.grid[i][0] == this.grid[i][1] && this.grid[i][1] == this.grid[i][2]) { // e forem todos iguais
                    if (this.grid[i][0]) {
                        return 'O';
                    } else return 'X';
                }
            }
        }

        //check rows
        for (int i = 0; i < 3; i++) {
            if(this.grid[0][i]!=null&&this.grid[1][i]!=null && this.grid[2][i]!=null) {
                if (this.grid[0][i] == this.grid[1][i] && this.grid[1][i]== this.grid[2][i]) {
                    if (this.grid[0][i]) {
                        return 'O';
                    } else return 'X';
                }
            }
        }

        //check diagonal
        if(this.grid[0][0]!=null&&this.grid[1][1]!=null && this.grid[2][2]!=null)
            if (this.grid[0][0] == this.grid[1][1] && this.grid[1][1]== this.grid[2][2]) {
                if (this.grid[0][0]) {
                    return 'O';
                } else return 'X';
            }

        //check anti-diagonal
        if(this.grid[0][2]!=null&&this.grid[1][1]!=null && this.grid[2][0]!=null)
            if (this.grid[0][2] == this.grid[1][1]&& this.grid[1][1] == this.grid[2][0]) {
                if (this.grid[0][2]) {
                    return 'O';
                } else return 'X';
            }

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j] != null) {
                    counter++;
                }
            }
        }
        //It's a Tie!
        if (counter == 9)
            return ' ';

        //Should be unreachable o.O
        return 0;
    }
}
