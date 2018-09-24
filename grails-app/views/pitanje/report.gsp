<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'pitanje.label', default: 'Pitanje')}" />
        <title>Statistika glasanja za pitanje: ${pitanje.tekst}</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
    </head>
    
    <body>

        <div id="show-pitanje" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <h3>${pitanje.tekst}</h3>

            Filter:
            <g:form  METHOD="POST" action="report" id="${pitanje.id}">
                <fieldset>
                <div class = "fieldcontain">
                    <label for="pol">Pol</label>
                    <g:select name="pol" from="${['M', 'Z']}" noSelection="['':'-Odaberite pol-']"
                              value="${pol}"/>
                </div>
                <div class = "fieldcontain">
                    <label for="godiste">Godiste</label>
                    <g:select name="godiste" from="${1950..2010}" value="${godiste}"
                              noSelection="['':'-Odaberite godiste-']"/>
                </div>
                </fieldset>
                <fieldset class="buttons">
                    <input type="submit" value="Primeni" />
                </fieldset>
            </g:form>
            <h1>Broj odbranih odgovora</h1>
            <canvas id="izborCount" class="chart"></canvas>
            <h1>Procenti odabranih odgovora</h1>
            <canvas id="izborPie" class="chart"></canvas>


        </div>
        <script>

        window.chartColors = {
            red: 'rgb(255, 99, 132)',
            orange: 'rgb(255, 159, 64)',
            yellow: 'rgb(255, 205, 86)',
            green: 'rgb(75, 192, 192)',
            blue: 'rgb(54, 162, 235)',
            purple: 'rgb(153, 102, 255)',
            grey: 'rgb(201, 203, 207)'
        };

        var izboriCount =[
             <g:each in="${izboriCount}" var="i">
                        ${i},
            </g:each>
        ];

        var izboriSum = 0;
        for(i=0; i<izboriCount.length;i++){
            izboriSum+=izboriCount[i]
        }

        var izboriPercentages = []
        for(i=0; i<izboriCount.length;i++){
            izboriPercentages.push(parseFloat(izboriCount[i])/parseFloat(izboriSum) * 100);
        }
        

        var odgovoriTekst = [ 
            <g:each in="${pitanje.odgovori}" var="odgovor">
                        "${odgovor.tekst}",
            </g:each>
        ];

        var config = {
			type: 'bar',
			data: {
				datasets: [{
				    label: 'Filtriran set',
					data: izboriCount,
                    backgroundColor: [
						window.chartColors.red,
						window.chartColors.orange,
						window.chartColors.yellow,
						window.chartColors.green,
						window.chartColors.blue,
					],
				}],
				labels: odgovoriTekst
			},
			options: {
				responsive: true,
                scales: {
				    yAxes:[{
				        ticks:{
				            min:0
                        }
                    }]
                }
			}
		};

        var configPie = {
			type: 'pie',
			data: {
				datasets: [{
					data: izboriPercentages,
                    backgroundColor: [
						window.chartColors.red,
						window.chartColors.orange,
						window.chartColors.yellow,
						window.chartColors.green,
						window.chartColors.blue,
					],
				}],
				labels: odgovoriTekst
			},
			options: {
				responsive: true
			}
		};

        window.onload = function() {
			var ctx = document.getElementById('izborCount').getContext('2d');
			window.bar = new Chart(ctx, config);

            var ctx2 = document.getElementById('izborPie').getContext('2d');
			window.pie = new Chart(ctx2, configPie);
		};

    </script>
    </body>
</html>
