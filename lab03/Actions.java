package lab03;

public class Actions implements JGaloInterface  {

    private char[][] buttons;
    private char currentPlayer; 
    private int plays;

    public Actions(){               //construtor sem argumento
        currentPlayer = 'X';        
        buttons = new char[3][3];   //matriz 3x3 com valor default nulo (0)
        plays = 0;                  
    }

    public Actions(char arg){       //construtor com argumento passado (símbolo inicial)
        currentPlayer = arg;
        buttons = new char[3][3];
        plays = 0;
    }

    public char getActualPlayer() {
        return this.currentPlayer;
    }

	public boolean setJogada(int lin, int col) {
        buttons[lin-1][col-1] = this.currentPlayer;
        this.plays++;
        return true;
    }
	
    public boolean isFinished() {                       
        if (this.plays == 9 || checkResult() != ' ') {  //verifica se foram executadas 9 jogadas ou se algum jogador ganhou
            return true;
        } else {
            this.currentPlayer = this.getActualPlayer() == 'X' ? 'O' : 'X'; //ainda não acabou o jogo, trocar de jogador
            return false;
        }
    }
	
    public char checkResult() {

        char result = ' ';   //empate (valor default)
        
        //Verificar linhas completas
        for (int l = 0; l < 3; l++) {
            if (this.buttons[l][0] == this.buttons[l][1] && this.buttons[l][1] == this.buttons[l][2] && this.buttons[l][0] != 0) {
                result = this.buttons[l][0];
            }
        }

        //Verificar colunas completas
        for (int c = 0; c < 3; c++) {
            if (this.buttons[0][c] == this.buttons[1][c] && this.buttons[1][c] == this.buttons[2][c] && this.buttons[0][c] != 0) {
                result = this.buttons[0][c];
            }
        }

        //Verificar diagonais completas
        if (this.buttons[0][0] == this.buttons[1][1] && this.buttons[1][1] == this.buttons[2][2] && this.buttons[0][0] != 0) {
            result = this.buttons[0][0];
        } else if (this.buttons[2][0] == this.buttons[1][1] && this.buttons[1][1] == this.buttons[0][2] && this.buttons[2][0] != 0) {
            result = this.buttons[2][0];
        }
        
        return result;
    }

}