import {
    ListFilter,
    MoreHorizontal,
    PlusCircle,
} from "lucide-react"

import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import {
    DropdownMenu,
    DropdownMenuCheckboxItem,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
} from "@/components/ui/table"
import {
    Tabs,
    TabsContent,
} from "@/components/ui/tabs"

import apiClient from "@/data/apiClient";
import { useQuery } from "@tanstack/react-query";
import { useCallback } from "react"


function LandingPage() {

    const wordsQuery = useQuery({
        queryKey: ["word"],
        queryFn: async() =>{
            const res = await apiClient.wordsApi.apiWordsAllGet()
            return res.data
        },
    })

    const buildWordsTable = useCallback(() => {
        return(
            wordsQuery.data?.map((word) => {
                return(
                    <TableRow key={word.id}>
                        <TableCell>
                            <span className="text-sm text-gray-500">{word.id}</span>
                        </TableCell>
                        <TableCell>
                            <span className="text-sm text-gray-500">{word.english}</span>
                        </TableCell>
                        <TableCell>
                            <span className="text-sm text-gray-500">{word.italian}</span>
                        </TableCell>
                        <TableCell>
                            <span className="text-sm text-gray-500">{word.category}</span>
                        </TableCell>
                        <TableCell>
                            <span className="text-sm text-gray-500">{word.createdAt}</span>
                        </TableCell>
                    </TableRow>
                )
            }
        )
        );
    }, [wordsQuery.data])


    return (
            <div>
                <main>
                    <Tabs defaultValue="all">
                        <div className="flex items-center">
                            <div className="ml-auto flex items-center gap-2">
                                <DropdownMenu>
                                    <DropdownMenuTrigger asChild>
                                        <Button variant="outline" size="sm" className="h-8 gap-1">
                                            <ListFilter className="h-3.5 w-3.5" />
                                            <span className="sr-only sm:not-sr-only sm:whitespace-nowrap">
                                                Filter
                                            </span>
                                        </Button>
                                    </DropdownMenuTrigger>
                                    <DropdownMenuContent align="end">
                                        <DropdownMenuLabel>Filter by</DropdownMenuLabel>
                                        <DropdownMenuSeparator />
                                        <DropdownMenuCheckboxItem checked>
                                            Active
                                        </DropdownMenuCheckboxItem>
                                        <DropdownMenuCheckboxItem>Draft</DropdownMenuCheckboxItem>
                                        <DropdownMenuCheckboxItem>
                                            Archived
                                        </DropdownMenuCheckboxItem>
                                    </DropdownMenuContent>
                                </DropdownMenu>
                                <Button size="sm" className="h-8 gap-1">
                                    <PlusCircle className="h-3.5 w-3.5" />
                                    <span className="sr-only sm:not-sr-only sm:whitespace-nowrap">
                                        Add Product
                                    </span>
                                </Button>
                            </div>
                        </div>
                        <TabsContent value="all">
                            <Card x-chunk="dashboard-06-chunk-0">
                                <CardHeader>
                                    <CardTitle>Words</CardTitle>
                                    <CardDescription>
                                        Manage your words.
                                    </CardDescription>
                                </CardHeader>
                                <CardContent>
                                    <Table>
                                        <TableHeader>
                                            <TableRow>
                                                <TableHead className="hidden w-[100px] sm:table-cell">
                                                    <span className="sr-only">Image</span>
                                                </TableHead>
                                                <TableHead>Number</TableHead>
                                                <TableHead>English</TableHead>
                                                <TableHead>Italian</TableHead>
                                                <TableHead>Category</TableHead>
                                                <TableHead>Created At</TableHead>
                                            </TableRow>
                                        </TableHeader>
                                        <TableBody>
                                            {buildWordsTable()}
                                        </TableBody>
                                    </Table>
                                </CardContent>
                            </Card>
                        </TabsContent>
                    </Tabs>
                </main>
            </div>
    )
}

export default LandingPage
